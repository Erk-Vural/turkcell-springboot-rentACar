package com.erkvural.rentacar.business.abstracts;

import com.erkvural.rentacar.business.dtos.ListColorDto;
import com.erkvural.rentacar.business.requests.CreateColorRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ColorService {
    List<ListColorDto> getAll();

    void add(CreateColorRequest createColorRequest);

    ListColorDto getColorByColorId(int id);
}
