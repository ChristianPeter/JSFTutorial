/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.java.jsftutorial.converter;

import java.util.HashMap;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sol.java.jsftutorial.ticketing.entity.AbstractEntity;

/**
 *
 * @author debiandev
 */
@FacesConverter("entityConverter")
public class EntityConverter implements Converter {

    private static final String key = "com.example.jsf.EntityConverter";
    private static final String empty = "";

    private Map<String, Object> getViewMap(FacesContext context) {
        Map<String, Object> viewMap = context.getViewRoot().getViewMap();
        @SuppressWarnings({"unchecked", "rawtypes"})
        Map<String, Object> idMap = (Map) viewMap.get(key);
        if (idMap == null) {
            idMap = new HashMap<>();
            viewMap.put(key, idMap);
        }
        return idMap;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent c, String value) {
        if (value.isEmpty()) {
            return null;
        }
        return getViewMap(context).get(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent c, Object value) {
        if (value == null) {
            return empty;
        }
        String id = ((AbstractEntity) value).getUuid();
        getViewMap(context).put(id, value);
        return id;
    }
}
