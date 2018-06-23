
package devdem.time;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ChangePassRequest extends StringRequest {
    private static final String UPDATE_REQUEST_URL = "http://devdem.ru/Reminder/updpass.php";
    private Map<String, String> params;

    ChangePassRequest(String username, String password, String newpass, Response.Listener<String> listener) {
        super(Method.POST, UPDATE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("newpassword", newpass);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

