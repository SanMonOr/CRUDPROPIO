/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejemplo.java.springboot.ejemplo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author santiago
 */

@RestController
@RequestMapping("/hello/v1")
public class HelloController {
	
	// Creación de elementos
	private List<String> items = new ArrayList<String>();
	
	// Valores de prueba en constructor
	public HelloController() {
		items.add("Colombia");
		items.add("Brasil");
		items.add("Perú");
		items.add("Chile");
		items.add("Venezuela");
		items.add("Bolivia");
	}
	
	// Consulta de elementos
	// 1. Cuando un usuario consulta mediante GET la url/hello/v1, retornará todos los items de la lista "items".
	@GetMapping
    //public List<String> getAllItems() {
	public Map<String, Object> getAllItems() {
		Map<String, Object> response = new HashMap<>(); // para json (hashmap)
		response.put("items", items);
		response.put("count", items.size());
		return response;
        // return items;
    }
	
	@GetMapping("/{index}")
	public String getItem(@PathVariable int index) {
		if (index >= 0 && index < items.size()) {
			return items.get(index);
		}
		else {
			return "Item no encontrado";
		}
	}
 
	// Creacion de elementos
    @PostMapping
    public String agregarItem(@RequestBody String newItem) {
        items.add(newItem);
        return "Item insertado con exito";
    }
    
    // Actualización de elementos
    @PutMapping("/{index}")
    public String modifyItem(@PathVariable int index, @RequestBody String newItem) {
    	if (index >= 0 && index < items.size()) {
    		items.set(index,  newItem);
    		return "Item actualizado";
    	} else {
    		return "Item no encontrado";
    	}
    }
    
    // Eliminación de elementos
    @DeleteMapping("/{index}")
    public String deleteItem(@PathVariable int index) {
    	if (index >= 0 && index < items.size()) {
			items.remove(index);
			return "Item eliminado con exito";
		}
		else {
			return "Item no eliminado";
		}
    }
}