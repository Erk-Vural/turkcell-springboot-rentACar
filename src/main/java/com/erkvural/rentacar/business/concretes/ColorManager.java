package com.erkvural.rentacar.business.concretes;

import com.erkvural.rentacar.business.abstracts.ColorService;
import com.erkvural.rentacar.business.dtos.ColorDto;
import com.erkvural.rentacar.business.requests.color.CreateColorRequest;
import com.erkvural.rentacar.business.requests.color.DeleteColorRequest;
import com.erkvural.rentacar.business.requests.color.UpdateColorRequest;
import com.erkvural.rentacar.core.utilities.mapping.ModelMapperService;
import com.erkvural.rentacar.core.utilities.results.*;
import com.erkvural.rentacar.dataaccess.abstracts.ColorDao;
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
    public DataResult<List<ColorDto>> getAll() {
        List<Color> result = colorDao.findAll();
        List<ColorDto> response = result.stream()
                .map(color -> modelMapperService.forDto()
                        .map(color, ColorDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<ColorDto>>("Success", response);
    }

    @Override
    public DataResult<ColorDto> getById(int id) {
        Color color = colorDao.getById(id);

        if (color != null) {
            ColorDto response = modelMapperService.forDto().map(color, ColorDto.class);

            return new SuccessDataResult<ColorDto>("Color with given ID found.", response);
        }

        return new ErrorDataResult<ColorDto>("Color with given ID can't be found.");
    }

    @Override
    public Result add(CreateColorRequest createColorRequest) {
        Color color = modelMapperService.forRequest().map(createColorRequest, Color.class);

        if (!checkColorNameExist(color)) {
            this.colorDao.save(color);

            return new SuccessResult("Color added: " + color.getName());
        }
        return new ErrorResult("Color can't be added (Color with same name exists) " + color.getName());
    }

    @Override
    public Result update(UpdateColorRequest updateColorRequest) {
        Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);

        if (checkColorIdExist(color)) {
            this.colorDao.save(color);

            return new SuccessResult("Color updated: " + color.getName());
        }
        return new ErrorResult("Color can't be updated (Color with given Id not exists) " + color.getName());
    }

    @Override
    public Result delete(DeleteColorRequest deleteColorRequest) {
        Color color = this.modelMapperService.forRequest().map(deleteColorRequest, Color.class);

        if (checkColorIdExist(color)) {
            this.colorDao.deleteById(color.getId());

            return new SuccessResult("Color deleted with id: " + color.getId());
        }
        return new ErrorResult("Color can't be deleted (Color with given Id not exists) " + color.getId());
    }

    private boolean checkColorIdExist(Color color) {

        return Objects.nonNull(colorDao.getColorById(color.getId()));
    }

    private boolean checkColorNameExist(Color color) {
        return Objects.nonNull(colorDao.getColorByName(color.getName()));
    }

}
