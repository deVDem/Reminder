package devdem.time;
// импорты

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

// бля

public class DeleteAccountRequest extends StringRequest {
    private static final String UPDATE_REQUEST_URL = "http://devdem.ru/Reminder/delacc.php"; // ссылка на скрипт
    private Map<String, String> params;

    DeleteAccountRequest(String username, String password, Response.Listener<String> listener) {
        super(Method.POST, UPDATE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username); // лагин
        params.put("password", password); // пороль
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


