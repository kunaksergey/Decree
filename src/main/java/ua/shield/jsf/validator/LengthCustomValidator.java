package ua.shield.jsf.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.LengthValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by sa on 21.06.17.
 */
@FacesValidator("lengthCustomValidator")
public class LengthCustomValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object obj) throws ValidatorException {
        int minimum=Integer.parseInt((String)component.getAttributes().get("minimum"));
        int maximum=Integer.parseInt((String)component.getAttributes().get("maximum"));
        String msg=(String)component.getAttributes().get("message");
        String value = obj.toString();
        if(value.length()<minimum||value.length()>maximum){
            FacesMessage msgFaces = new FacesMessage(msg);
            msgFaces.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msgFaces);
        }

    }
}
