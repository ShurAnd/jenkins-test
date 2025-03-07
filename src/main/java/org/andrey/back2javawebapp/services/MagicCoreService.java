package org.andrey.back2javawebapp.services;

import org.andrey.back2javawebapp.exceptions.MagicCoreEntryNotFoundException;
import org.andrey.back2javawebapp.exceptions.NotEnoughCoreException;
import org.andrey.back2javawebapp.exceptions.NotEnoughCoreForTransferringException;
import org.andrey.back2javawebapp.model.CoreTransferData;
import org.andrey.back2javawebapp.model.MagicCore;
import org.andrey.back2javawebapp.repositories.DataRepository;
import org.andrey.back2javawebapp.repositories.MagicCoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MagicCoreService {
    private final DataRepository repository;
    private final MagicCoreRepository magicCoreRepository;

    @Autowired
    public MagicCoreService(DataRepository repository,
                            MagicCoreRepository magicCoreRepository){
        this.repository = repository;
        this.magicCoreRepository = magicCoreRepository;
    }

    public MagicCore saveMagicCore(MagicCore magicCore){
        if (magicCore.getQuantity().compareTo(new BigDecimal(10)) <= 0){
            throw new NotEnoughCoreException("Where is my core?!");
        }
        long id = repository.save(magicCore);
        magicCore.setId(id);

        return magicCore;
    }

    public MagicCore updateMagicCore(MagicCore magicCore){
        repository.update(magicCore);
        return magicCore;
    }

    public boolean deleteMagicCore(long id){
        repository.deleteOneById(id);

        return true;
    }

    public MagicCore findOneById(long id){
        return  repository.getOneById(id);
    }

    public List<MagicCore> getAll(){
        return repository.getAll();
    }

    public MagicCore save(MagicCore magicCore){
        return magicCoreRepository.save(magicCore);
    }

    public MagicCore update(MagicCore magicCore){
        return magicCoreRepository.save(magicCore);
    }

    public MagicCore getOne(Long id){
        return  magicCoreRepository.findById(id).orElse(null);
    }

    public List<MagicCore> findAll(){
        List<MagicCore> result = new ArrayList<>();
        magicCoreRepository.findAll().forEach(result::add);
        return result;
    }

    public List<MagicCore> findAllByOwner(String owner){
        List<MagicCore> result = new ArrayList<>();
        result = magicCoreRepository.findByOwner(owner);

        return result;
    }

    public boolean delete(Long id){
        magicCoreRepository.deleteById(id);
        return true;
    }

    public boolean transferCoreToAnother(CoreTransferData data){
        MagicCore mc1 = magicCoreRepository.findById(data.getSource()).orElseThrow(() -> new MagicCoreEntryNotFoundException("Incorrect id " + data.getSource()));
        MagicCore mc2 = magicCoreRepository.findById(data.getDest()).orElseThrow(() -> new MagicCoreEntryNotFoundException("Incorrect id " + data.getDest()));
        if (mc1.getQuantity().compareTo(data.getAmount()) < 0){
            throw new NotEnoughCoreForTransferringException("Incorrect amount of core. Source have " + mc1.getQuantity() + " " +
                    " but trying send " + data.getAmount());
        }

        magicCoreRepository.updateCoreAmount(data.getSource(), mc1.getQuantity().subtract(data.getAmount()));
        magicCoreRepository.updateCoreAmount(data.getDest(), mc2.getQuantity().add(data.getAmount()));

        return true;
    }
}
