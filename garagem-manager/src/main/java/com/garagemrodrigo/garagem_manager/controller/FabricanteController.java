package com.garagemrodrigo.garagem_manager.controller;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.garagemrodrigo.garagem_manager.model.Fabricante;
import com.garagemrodrigo.garagem_manager.repository.FabricanteRepository;

import ch.qos.logback.core.testUtil.RandomUtil;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/fabricante")
public class FabricanteController {
    
    @Autowired
    private FabricanteRepository fabricanteRepository;

    @GetMapping("/list")
    public String index(Model modelView) {
        modelView.addAttribute("listaFabricante", fabricanteRepository.findAll());
        return "lista-fabricante";
    }
    @GetMapping("/create")
    public String createFabricante() {
        return "criar-fabricante";
    }
    @PostMapping("/save")
    public String saveFabricante(@ModelAttribute Fabricante fabricante) {
        fabricante.setId(RandomUtil.getPositiveInt());
        fabricante.setCreatedDate(LocalDate.now());
        fabricanteRepository.save(fabricante);
        return "redirect:/fabricante/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteFabricante(@PathVariable ("id") Integer id) {
        Optional<Fabricante> optFabricante = fabricanteRepository.findById(id);
        if (optFabricante.isPresent()) {
            fabricanteRepository.delete(optFabricante.get());
        }
        return "redirect:/fabricante/list";
    }
        
    @GetMapping("/edit/{id}")
    public String edit(Model modelAndView, @PathVariable("id") Integer id) {
        Optional <Fabricante> optFabricante = fabricanteRepository.findById(id);
        modelAndView.addAttribute("editFabricante", optFabricante.get());
        return "editar-fabricante";
    }
    @PostMapping("/edit/{id}")
    public String editFabricante(@PathVariable("id") Integer id, @ModelAttribute Fabricante newData) {
        Optional<Fabricante> optFabricante = fabricanteRepository.findById(id);

        Fabricante fabricante = optFabricante.get();
        fabricante.setName(newData.getName());    
        fabricanteRepository.save(fabricante);

        return "redirect:/fabricante/list";
    }
    

    
}
