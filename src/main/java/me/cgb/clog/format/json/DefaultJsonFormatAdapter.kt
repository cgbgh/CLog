package me.cgb.clog.format.json

import android.util.Log
import me.cgb.clog.internal.Constants
import org.json.JSONArray
import org.json.JSONObject

/**
 * ================================================
 * @author cgb
 * @date 2025/1/5
 * @desc
 * <p>
 * ================================================
 */
class DefaultJsonFormatAdapter : IJsonFormatAdapter {

    override fun format(json: String): String {
        Log.d("cgb", "format() called with: json = $json")
        try {
            if (json.startsWith(Constants.BRACE_START)) {
                return JSONObject(json).toString(Constants.INDENT_SPACE)
            } else if (json.startsWith(Constants.SQUARE_BRACKETS_START)) {
                return JSONArray(json).toString(Constants.INDENT_SPACE)
            }
        } catch (ignore: Exception) {
        }
        return json
    }
}