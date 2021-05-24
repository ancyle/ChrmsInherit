package user.ancyle.chrms.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import user.ancyle.chrms.business.abstracts.PositionService;
import user.ancyle.chrms.core.utilities.result.DataResult;
import user.ancyle.chrms.core.utilities.result.ErrorResult;
import user.ancyle.chrms.core.utilities.result.Result;
import user.ancyle.chrms.core.utilities.result.SuccessDataResult;
import user.ancyle.chrms.entities.concretes.Position;

import java.util.List;

@RestController
@RequestMapping("/pst")
public class PositionsController {

    private final PositionService positionService;

    @Autowired
    public PositionsController(PositionService positionService){
        this.positionService=positionService;
    }

    @GetMapping("/new")
    public Result newPosition(@RequestBody Position position){
        var result=this.positionService.newPosition(position);
        if(!result.isSuccess()) return new ErrorResult(result.getMessage());
        return result;
    }

    @PostMapping("/all")
    public DataResult<List<Position>> listPositions(){
        return new SuccessDataResult<>(this.positionService.listAllPositions().getData());
    }
}
