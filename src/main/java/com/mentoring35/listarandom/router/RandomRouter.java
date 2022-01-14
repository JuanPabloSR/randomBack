package com.mentoring35.listarandom.router;

import com.google.common.collect.Lists;
import com.mentoring35.listarandom.collection.Parametros;
import com.mentoring35.listarandom.collection.Random;
import com.mentoring35.listarandom.mapper.MapperUitls;
import com.mentoring35.listarandom.model.RequestDTO;
import com.mentoring35.listarandom.model.RequestParamsDTO;
import com.mentoring35.listarandom.repository.RandomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
@CrossOrigin("*")
public class RandomRouter {

    @Autowired
    private RandomRepository repository;

    MapperUitls mapperUitls = new MapperUitls();

    @PostMapping("/new")
    public Mono<RequestDTO> forNumber(@RequestBody RequestParamsDTO request) {
        return Mono.just(new Random()).map(entity -> {
            entity.setDate(new Date());
            entity.setOriginalList(IntStream.range(request.getValorInicial(), request.getValorMaximo() + 1)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(",")));
            return entity;
        }).map(entity -> {
            var list = Stream.of(entity.getOriginalList().split(","))
                    .collect(Collectors.toList());
            Collections.shuffle(list);
            var size = (list.size()/ request.getCantidadColumnas());
            entity.setRandomList(Lists.partition(list, size));
            entity.setParametros(new Parametros(request.getValorInicial(), request.getValorMaximo()));
            return entity;
        }).flatMap(repository::save).map(random -> mapperUitls.randomToRequestDTO().apply(random));
    }

    @GetMapping("result/{id}")
    public Mono<Random> get(@PathVariable String id) {
        return repository.findById(id);
    }
}
