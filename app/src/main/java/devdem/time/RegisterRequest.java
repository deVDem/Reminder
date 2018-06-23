package devdem.time;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://devdem.ru/Reminder/Register.php";
    private Map<String, String> params;

    RegisterRequest(String name, String username, String email, String password, String zagolovok, String opisanie, int spam, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("username", username);
        params.put("password", password);
        params.put("zagolovok", zagolovok);
        params.put("opisanie", opisanie);
        params.put("spam", spam + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
