
package devdem.time;
// импорты

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

// хуй

public class ChangePassRequest extends StringRequest {
    private static final String UPDATE_REQUEST_URL = "http://devdem.ru/Reminder/updpass.php"; // ссылочка на АБНАВЛЕНИЕ ПОРОЛЯ
    private Map<String, String> params;

    ChangePassRequest(String username, String password, String newpass, Response.Listener<String> listener) {
        super(Method.POST, UPDATE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username); // опять логин
        params.put("password", password); // Я ЗНАЮ ПАРОЛЬ, Я ВИДУ ОРИЕНТИР
        params.put("newpassword", newpass); // новый пароль
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

