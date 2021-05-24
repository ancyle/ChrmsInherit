package user.ancyle.chrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.ancyle.chrms.business.abstracts.PositionService;
import user.ancyle.chrms.business.constants.Messages;
import user.ancyle.chrms.core.utilities.business.RuleVerifier;
import user.ancyle.chrms.core.utilities.result.*;
import user.ancyle.chrms.dataAccess.abstracts.PositionRepo;
import user.ancyle.chrms.entities.concretes.Position;

import java.util.List;
@Service
public class PositionManager implements PositionService {

    private final PositionRepo positionRepo;

    //Constructor
    @Autowired
    public PositionManager(PositionRepo positionRepo) {
        this.positionRepo = positionRepo;
    }


    //Business
    @Override
    public Result newPosition(Position position) {
        var result= RuleVerifier.verify(checkPositionExists(position.getPoName()));
        if(!result.isSuccess()) return new ErrorResult(result.getMessage());
        this.positionRepo.saveAndFlush(position);
        return new SuccessResult(Messages.success);
    }

    @Override
    public DataResult<List<Position>> listAllPositions() {
        return new SuccessDataResult<>(this.positionRepo.findAll(),Messages.success);
    }

    @Override
    public DataResult<Position> getPosition(short id) {
        return new SuccessDataResult<Position>(this.positionRepo.findById(id).get(),Messages.success);
    }

    @Override
    public Result updatePosition(Position position) {
        var result= RuleVerifier.verify(checkPositionExists(position.getPoName()));
        if(!result.isSuccess()) return new ErrorResult(result.getMessage());
        this.positionRepo.save(position);
        return new SuccessResult(Messages.success);
    }

    @Override
    public Result deletePositionById(short id) {
        this.positionRepo.deleteById(id);
        return new SuccessResult(Messages.success);
    }

    //Business Rules

    private Result checkPositionExists(String name){
        var result=this.positionRepo.findByPoName(name);
        if(result.isPresent()) return new ErrorResult(Messages.positionExists);
        return new SuccessResult();
    }
}
