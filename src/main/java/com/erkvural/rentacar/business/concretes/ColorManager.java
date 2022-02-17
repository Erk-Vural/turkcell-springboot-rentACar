package com.erkvural.rentacar.business.concretes;

import com.erkvural.rentacar.business.abstracts.ColorService;
import com.erkvural.rentacar.business.dtos.ListColorDto;
import com.erkvural.rentacar.business.requests.color.CreateColorRequest;
import com.erkvural.rentacar.business.requests.color.DeleteColorRequest;
import com.erkvural.rentacar.business.requests.color.UpdateColorRequest;
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
    public ListColorDto getById(int id) {
        Color color = colorDao.getById(id);
        ListColorDto response = modelMapperService.forDto().map(color, ListColorDto.class);

        return response;
    }

    private boolean checkColorNameExist(Color color) {
        return Objects.nonNull(colorDao.getColorByColorName(color.getColorName()));
    }

    @Override
    public void add(CreateColorRequest createColorRequest) {
        Color color = modelMapperService.forRequest().map(createColorRequest, Color.class);

        if(!checkColorNameExist(color)) {
            colorDao.save(color);
        }
    }

    @Override
    public void update(UpdateColorRequest updateColorRequest) {
        Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);

        if(this.colorDao.getColorByColorId(color.getColorId()) == null) {
            System.out.println("Can't find color by Id to update");
        }

        this.colorDao.save(color);
    }

    @Override
    public void delete(DeleteColorRequest deleteColorRequest) {
        Color color = this.modelMapperService.forRequest().map(deleteColorRequest, Color.class);

        if(this.colorDao.getColorByColorId(color.getColorId()) == null) {
            System.out.println("Can't find color by Id to delete");
        }

        this.colorDao.deleteById(color.getColorId());
    }
}
