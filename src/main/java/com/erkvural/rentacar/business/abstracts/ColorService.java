package com.erkvural.rentacar.business.abstracts;

import com.erkvural.rentacar.business.dtos.ColorDto;
import com.erkvural.rentacar.business.requests.color.CreateColorRequest;
import com.erkvural.rentacar.business.requests.color.DeleteColorRequest;
import com.erkvural.rentacar.business.requests.color.UpdateColorRequest;
import com.erkvural.rentacar.core.utilities.results.DataResult;
import com.erkvural.rentacar.core.utilities.results.Result;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.List;

@Service
public interface ColorService {
    DataResult<List<ColorDto>> getAll();

    DataResult<ColorDto> getById(int id);

    Result add(CreateColorRequest createColorRequest);

    Result update(UpdateColorRequest updateColorRequest);

    Result delete(DeleteColorRequest deleteColorRequest);
}
