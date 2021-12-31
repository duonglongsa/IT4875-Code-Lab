package com.example.gmailui;

import com.example.gmailui.model.Address;
import com.example.gmailui.model.Avatar;
import com.example.gmailui.model.Company;
import com.example.gmailui.model.Geo;
import com.example.gmailui.model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class Utils {
    public static String BASE_URL = "https://lebavui.github.io";
    public static User Mapping(JSONObject user) throws JSONException {
        User mUser = new User(user.getInt("id"),
                user.getString("name"),
                user.getString("username"),
                user.getString("email"),
                new Avatar(user.getJSONObject("avatar").getString("thumbnail"),
                        user.getJSONObject("avatar").getString("photo")),
                new Address(user.getJSONObject("address").getString("street"),
                        user.getJSONObject("address").getString("suite"),
                        user.getJSONObject("address").getString("city"),
                        user.getJSONObject("address").getString("zipcode"),
                        new Geo(user.getJSONObject("address").getJSONObject("geo").getString("lat"),
                                user.getJSONObject("address").getJSONObject("geo").getString("lng"))),
                user.getString("phone"),
                user.getString("website"),
                new Company(user.getJSONObject("company").getString("name"),
                        user.getJSONObject("company").getString("catchPhrase"),
                        user.getJSONObject("company").getString("bs"))
        );
        return mUser;
    }
}