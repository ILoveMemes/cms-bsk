package com.cms.megaprint.common;

import com.cms.megaprint.services.CrudService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class CrudController<T, ID> {

    private final CrudService<T, ID> service;

    public CrudController(CrudService<T, ID> service) {
        this.service = service;
    }

    @GetMapping("/findAll")
    public @ResponseBody List<T> findAll() {
        return service.findAll();
    }

    @GetMapping("/findById/{id}")
    public @ResponseBody T findById(@PathVariable ID id) {
        Optional<T> val = service.findById(id);
        if (val.isPresent()) {
            return val.get();
        }
        return null;
    }

    @PostMapping("/save")
    public @ResponseBody T save(@ModelAttribute("object") T object) {
        return service.save(object);
    }

    @PostMapping("/update")
    public @ResponseBody T update(@ModelAttribute("object") T object) {
        return service.update(object);
    }

    @PostMapping("/delete")
    public @ResponseBody boolean delete(@ModelAttribute("object") T object) {
        return service.delete(object);
    }

    @GetMapping("/deleteById/{id}")
    public @ResponseBody boolean deleteById(@PathVariable ID id) {
        return service.deleteById(id);
    }

}
