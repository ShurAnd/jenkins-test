package org.andrey.back2javawebapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.andrey.back2javawebapp.model.CoreTransferData;
import org.andrey.back2javawebapp.model.MagicCore;
import org.andrey.back2javawebapp.services.MagicCoreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {
    private static final Logger logger = LogManager.getLogger(MainController.class);

    private final MagicCoreService service;
    private final ObjectMapper objectMapper;

    @Autowired
    public MainController(MagicCoreService service,
                          ObjectMapper objectMapper){
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/hello1/{name}")
    public String getMain(@PathVariable String name, Model model, @RequestParam(value = "color", defaultValue = "black") String color){
        logger.info(name);
        model.addAttribute("name", name);
        model.addAttribute("color", color);
        return "index.html";
    }

    @GetMapping("/core/magic")
    public ResponseEntity<String> findAll() throws Exception{
        logger.info("Request for getting list of magic core");
        //List<MagicCore> magicCoreList = service.getAll();
        List<MagicCore> magicCoreList = service.findAll();
        logger.info("Request for getting list of magic core processed");
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(objectMapper.writeValueAsString(magicCoreList));
    }

    @GetMapping("/core/magic/{id}")
    public ResponseEntity<String> findOneById(@PathVariable Long id) throws Exception{
        logger.info("Request for getting magic core by id");
        //MagicCore magicCore = service.findOneById(id);
        MagicCore magicCore = service.getOne(id);
        logger.info("Request for getting magic core by id processed");
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(objectMapper.writeValueAsString(magicCore));
    }

    @PostMapping("/core/magic")
    public ResponseEntity<String> createOne(@RequestBody String mc) throws Exception{
        logger.info("Request for creating new entry of magic core");
        logger.info("data from user : {}", mc);
        MagicCore m = objectMapper.readValue(mc, MagicCore.class);
        //MagicCore magicCore = service.saveMagicCore(m);
        MagicCore magicCore = service.save(m);
        logger.info("Request for creating new entry of magic core processed");
        return ResponseEntity.created(null)
                .header("Content-Type", "application/json")
                .body(objectMapper.writeValueAsString(magicCore));
    }

    @PutMapping("/core/magic")
    public ResponseEntity<String> updateOne(@RequestBody String mc) throws Exception{
        logger.info("Request for updating entry of magic core");
        MagicCore m  = objectMapper.readValue(mc, MagicCore.class);
        //m = service.updateMagicCore(m);
        m = service.update(m);
        logger.info("Request for updating entry of magic core processed");
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(objectMapper.writeValueAsString(m));
    }

    @DeleteMapping("/core/magic/{id}")
    public ResponseEntity<?> deleteOneById(@PathVariable Long id) throws Exception{
        logger.info("Request for deleting entry of magic core");
        //service.deleteMagicCore(id);
        service.delete(id);
        logger.info("Request for deleting entry of magic core processed");
        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping("/core/magic/by/owner/{owner}")
    public ResponseEntity<String> findAllByOwner(@PathVariable String owner) throws Exception{
        logger.info("Request for getting list of magic core by owner {}", owner);
        //List<MagicCore> magicCoreList = service.getAll();
        List<MagicCore> magicCoreList = service.findAllByOwner(owner);
        logger.info("Request for getting list of magic core by owner {} processed", owner);
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(objectMapper.writeValueAsString(magicCoreList));
    }

    @PostMapping("/core/magic/transfer")
    public ResponseEntity<String> transferCore(@RequestBody String coreTransferData) throws Exception{
        CoreTransferData data = objectMapper.readValue(coreTransferData, CoreTransferData.class);
        boolean result = service.transferCoreToAnother(data);

        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public String test() {
    return "";
    }
}
