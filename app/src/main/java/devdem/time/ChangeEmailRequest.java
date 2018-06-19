
package devdem.time;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ChangeEmailRequest extends StringRequest {
    private static final String UPDATE_REQUEST_URL = "http://94.26.174.204/Reminder/updemail.php";
    private Map<String, String> params;

    ChangeEmailRequest(String username, String password, String newemail, Response.Listener<String> listener) {
        super(Method.POST, UPDATE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("newemail", newemail);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

