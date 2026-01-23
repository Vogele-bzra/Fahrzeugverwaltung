package Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class UserSpeicherService {

    private final String filename = "users.json";
    private ObjectMapper mapper = new ObjectMapper();

    public List<User> ladeUser() {
        File file = new File(filename);
        if (!file.exists()) {
            List<User> defaults = new ArrayList<>();
            defaults.add(new User("admin", hashPassword("1234")));
            speichereUser(defaults);
            return defaults;
        }

        try {
            return mapper.readValue(file, new TypeReference<List<User>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void speichereUser(List<User> userListe) {
        try {
            mapper.writeValue(new File(filename), userListe);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createUser(String username, String plainPassword) {
        List<User> users = ladeUser();

        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return;
            }
        }

        users.add(new User(username, hashPassword(plainPassword)));
        speichereUser(users);
    }

    public boolean checkCredentials(String username, String plainPassword) {
        List<User> users = ladeUser();
        String inputHash = hashPassword(plainPassword);

        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPasswordHash().equals(inputHash)) {
                return true;
            }
        }
        return false;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}