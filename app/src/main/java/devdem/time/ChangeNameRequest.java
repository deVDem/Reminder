package devdem.time;
// импорты

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

// да блять, что за говно то

public class ChangeNameRequest extends StringRequest {
    private static final String UPDATE_REQUEST_URL = "http://devdem.ru/Reminder/updname.php"; // ссылочка
    private Map<String, String> params;

    ChangeNameRequest(String username, String password, String newname, Response.Listener<String> listener) {
        super(Method.POST, UPDATE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username); // логин
        params.put("password", password); // пароль
        params.put("newname", newname); // новое имя
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
