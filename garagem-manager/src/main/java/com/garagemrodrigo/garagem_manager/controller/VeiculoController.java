package com.garagemrodrigo.garagem_manager.controller;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.garagemrodrigo.garagem_manager.model.Veiculo;
import com.garagemrodrigo.garagem_manager.repository.VeiculoRepository;

import ch.qos.logback.core.testUtil.RandomUtil;

import org.springframework.ui.Model;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/veiculo")
public class VeiculoController {
    
    @Autowired 
    private VeiculoRepository veiculoRepository;
    
    @GetMapping("/list")
    public String index(Model modelView) {
        modelView.addAttribute("listaVeiculo", veiculoRepository.findAll());
        return "lista-veiculo";
    }
    @GetMapping("/create")
    public String createVeiculo() {
        return "criar-veiculo";
    }
    @PostMapping("/save")
    public String saveVeiculo(@ModelAttribute Veiculo veiculo) {
        veiculo.setId(RandomUtil.getPositiveInt());
        veiculo.setCreatedDate(LocalDate.now());
        veiculoRepository.save(veiculo);
        return "redirect:/veiculo/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteVeiculo(@PathVariable("id") Integer id) {
        Optional <Veiculo> optVeiculo = veiculoRepository.findById(id);
        if (optVeiculo.isPresent()) {
            veiculoRepository.delete(optVeiculo.get());
        }    
        return "redirect:/veiculo/list";
    }
    @GetMapping("/edit/{id}")
    public String edit(Model modelandView, @PathVariable("id") Integer id) {
        Optional <Veiculo> optVeiculo = veiculoRepository.findById(id);

        modelandView.addAttribute("editVeiculo", optVeiculo.get());
        return "editar-veiculo";

    }
    @PostMapping("/edit/{id}")
    public String editVeiculo(@PathVariable("id") Integer id, @ModelAttribute Veiculo newData) {
        Optional<Veiculo> optVeiculo = veiculoRepository.findById(id);
        
        Veiculo veiculo = optVeiculo.get();

        veiculo.setName(newData.getName());
        veiculo.setType(newData.getType());
        veiculo.setDescription(newData.getDescription());
        veiculoRepository.save(veiculo);

        return "redirect:/veiculo/list";
    }
    
        
}
