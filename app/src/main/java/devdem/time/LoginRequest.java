package devdem.time;
// респонсы

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

// что это вообще?

public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://devdem.ru/Reminder/login.php"; // ссылка на скрипт логина :) ох ,как же я парился, блять с ним
    private Map<String, String> params; // а это значения, которые будем принимать и отправлять

    LoginRequest(String username, String password, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, errorListener);
        params = new HashMap<>();
        params.put("username", username); // отправка логина
        params.put("password", password); // отправка пароля, у, спизжу всё :)
    }

    @Override
    public Map<String, String> getParams() {
        return params; // получаем параметры
    }
}
