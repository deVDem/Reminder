
package devdem.time;
// импорты

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

// да что за срань то?

public class ChangeEmailRequest extends StringRequest {
    private static final String UPDATE_REQUEST_URL = "http://devdem.ru/Reminder/updemail.php"; // ссылочка на скрипт
    private Map<String, String> params; // инфа там всякая, например, что ты вчера делал(-а) вечером.......ммм.....♥

    ChangeEmailRequest(String username, String password, String newemail, Response.Listener<String> listener) {
        super(Method.POST, UPDATE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username); // логин
        params.put("password", password); // пароль
        params.put("newemail", newemail); // новый E-mail
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

