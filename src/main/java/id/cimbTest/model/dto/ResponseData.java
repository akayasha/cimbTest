package id.cimbTest.model.dto;

import id.cimbTest.model.entity.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class ResponseData {
    private boolean status;
    private List<String> message = new ArrayList<>();
    private Objects payload;

    public void setPayload(Role save) {
    }

    public void getPayload(List<Role> allRole) {
    }
}
