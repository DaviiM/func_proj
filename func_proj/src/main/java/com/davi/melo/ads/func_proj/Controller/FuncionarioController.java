package com.davi.melo.ads.func_proj.Controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.davi.melo.ads.func_proj.Model.*;
import com.davi.melo.ads.func_proj.Repository.FuncionarioRepository;

import java.util.List;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/funcionario")
@AllArgsConstructor
public class FuncionarioController {

    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public List<Funcionario> list(){
        return funcionarioRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Funcionario create(@RequestBody Funcionario funcionario){
        return (Funcionario) funcionarioRepository.save(funcionario);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        funcionarioRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    Optional<Object> update(@RequestBody Funcionario newFuncionario, @PathVariable Long id){
        return funcionarioRepository.findById(id).
                                    map(funcionario -> {
                                        ((Funcionario) funcionario).setNome(newFuncionario.getNome());
                                        ((Funcionario) funcionario).setEmail(newFuncionario.getEmail());
                                        ((Funcionario) funcionario).setCelular(newFuncionario.getCelular());
                                        ((Funcionario) funcionario).setCargo(newFuncionario.getCargo());
                                        ((Funcionario) funcionario).setSalario(newFuncionario.getSalario());
                                        ((Funcionario) funcionario).setDataAdmissao(newFuncionario.getDataAdmissao());
                                        return funcionarioRepository.save(funcionario);
                                    });
    }

    
}
