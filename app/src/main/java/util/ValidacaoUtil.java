package util;

import android.widget.EditText;

import com.oxi.bigu.R;
import com.oxi.bigu.controller.App;

public class ValidacaoUtil {

    public static boolean validarCamposVazios(EditText... editTexts) {
        boolean isValido = true;
        for (EditText editText : editTexts) {
            if  (editText.getText().toString().isEmpty()) {
                editText.setError(App.getContext().getResources().getString(R.string.campoObrigatorio));
                isValido = false;
            } else {
                editText.setError(null);
            }
        }
        return isValido;
    }
}
