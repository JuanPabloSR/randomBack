package com.mentoring35.listarandom.mapper;

import com.mentoring35.listarandom.collection.Random;
import com.mentoring35.listarandom.model.RequestDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUitls {

    public Function<Random, RequestDTO> randomToRequestDTO() {
        return update -> {
            var requestDTO = new RequestDTO();
            requestDTO.setId(update.getId());
            requestDTO.setRandomList(update.getRandomList());
            return requestDTO;
        };
    }

}
