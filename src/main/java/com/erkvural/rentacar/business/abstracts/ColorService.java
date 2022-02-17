package com.erkvural.rentacar.business.abstracts;

import com.erkvural.rentacar.business.dtos.ListColorDto;
import com.erkvural.rentacar.business.requests.color.CreateColorRequest;
import com.erkvural.rentacar.business.requests.color.DeleteColorRequest;
import com.erkvural.rentacar.business.requests.color.UpdateColorRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ColorService {
    List<ListColorDto> getAll();

    ListColorDto getById(int id);

    void add(CreateColorRequest createColorRequest);

    void update(UpdateColorRequest updateColorRequest);

    void delete(DeleteColorRequest deleteColorRequest);
}
