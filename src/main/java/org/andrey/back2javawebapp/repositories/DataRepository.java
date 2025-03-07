package org.andrey.back2javawebapp.repositories;

import org.andrey.back2javawebapp.model.MagicCore;

import java.util.List;

public interface DataRepository {
    List<MagicCore> getAll();
    MagicCore getOneById(Long id);
    Long save(MagicCore magicCore);
    Long update(MagicCore magicCore);
    Long deleteOneById(Long id);
}
