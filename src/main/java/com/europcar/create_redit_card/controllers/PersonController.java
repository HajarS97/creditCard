package com.europcar.create_redit_card.controllers;

import com.europcar.create_redit_card.mapper.PersonViewMapper;
import com.europcar.create_redit_card.service.IPersonService;
import com.europcar.create_redit_card.view.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("person")
public class PersonController {

    private final IPersonService personService;

    private final PersonViewMapper personViewMapper;

    public PersonController(IPersonService personService, PersonViewMapper personViewMapper) {
        this.personService = personService;
        this.personViewMapper = personViewMapper;
    }

    @PostMapping("/add")
    public PersonResponse createPerson(@RequestBody PersonRequest personRequest){
        return personViewMapper.dtoToResponse(personService.addPerson(personViewMapper.requestToDto(personRequest)));
    }


}

