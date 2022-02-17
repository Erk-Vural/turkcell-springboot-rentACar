package com.erkvural.rentacar.business.concretes;

import com.erkvural.rentacar.business.abstracts.ColorService;
import com.erkvural.rentacar.business.dtos.ListColorDto;
import com.erkvural.rentacar.business.requests.CreateColorRequest;
import com.erkvural.rentacar.core.utilities.mapping.ModelMapperService;
import com.erkvural.rentacar.dataAccess.abstracts.ColorDao;
import com.erkvural.rentacar.entities.concretes.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ColorManager implements ColorService {
    private final ColorDao colorDao;
    private final ModelMapperService modelMapperService;

    @Autowired
    public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
        this.colorDao = colorDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public List<ListColorDto> getAll() {
        List<Color> result = colorDao.findAll();
        List<ListColorDto> response = result.stream()
                .map(color -> modelMapperService.forDto()
                        .map(color, ListColorDto.class))
                            .collect(Collectors.toList());

        return response;
    }

    @Override
    public void add(CreateColorRequest createColorRequest) {
        Color color = modelMapperService.forRequest().map(createColorRequest, Color.class);

        if(!doesColorNameExist(color)) {
            colorDao.save(color);
        }
    }

    @Override
    public ListColorDto getColorByColorId(int id) {
        Color color = colorDao.getById(id);
        ListColorDto response = modelMapperService.forDto().map(color, ListColorDto.class);

        return response;
    }

    private boolean doesColorNameExist(Color color) {
        return Objects.nonNull(colorDao.getColorByColorName(color.getColorName()));
    }
}
