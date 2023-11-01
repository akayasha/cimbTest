package id.cimbTest.controller;

import id.cimbTest.helper.ERole;
import id.cimbTest.model.dto.ResponseData;
import id.cimbTest.model.dto.RolesDTO;
import id.cimbTest.model.entity.Role;
import id.cimbTest.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/roles")
public class RolesController {
    @Autowired
    private RoleService roleService;
    private Object create;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        ResponseData response = new ResponseData();
        try {
            response.setStatus(true);
            response.getMessage().add("success to get all data");
            response.getPayload(roleService.findAllRole());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            response.setStatus(false);
            response.getMessage().add("fail to get all data with error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody RolesDTO rolesDTO){
        ResponseData response = new ResponseData();
        return  ResponseEntity.ok().body(create);

    }


}

