package ua.shield.jsf.converter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;
import ua.shield.entity.User;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by sa on 07.08.17.
 */
@FacesConverter(value = "primeFacesPickListConverter")
public class PrimeFacesPickListConverte implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Object ret = null;
        if (uiComponent instanceof PickList) {
            Object dualList = ((PickList) uiComponent).getValue();
            DualListModel dl = (DualListModel) dualList;
            for (Object o : dl.getSource()) {
                String id = "" + ((User) o).getId();
                if (s.equals(id)) {
                    ret = o;
                    break;
                }
            }
            if (ret == null)
                for (Object o : dl.getTarget()) {
                    String id = "" + ((User) o).getId();
                    if (s.equals(id)) {
                        ret = o;
                        break;
                    }
                }
        }
        return ret;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        String str = "";
        if (o instanceof User) {
            str = "" + ((User) o).getId();
        }
        return str;
    }
}
