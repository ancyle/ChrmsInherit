package user.ancyle.chrms.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.ArrayUtils;
import user.ancyle.chrms.business.abstracts.UserService;
import user.ancyle.chrms.core.utilities.result.*;
import user.ancyle.chrms.entities.concretes.Employer;
import user.ancyle.chrms.entities.concretes.JobSeeker;
import user.ancyle.chrms.entities.concretes.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/usr")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService){
        this.userService=userService;
    }

    //Mapping

    @PostMapping("/new/emp")
    public Result newEmployer(@RequestBody Employer employer,String password){
        var result=this.userService.newEmployer(employer,password);
        if(!result.isSuccess()) return new ErrorResult(result.getMessage());
        return new SuccessResult(result.getMessage());
    }

    @PostMapping("/new/js")
    public Result newJobSeeker(@RequestBody JobSeeker jobSeeker,String password){
        var result=this.userService.newJobSeeker(jobSeeker,password);
        if(!result.isSuccess()) return new ErrorResult(result.getMessage());
        return new SuccessResult(result.getMessage());
    }

    @GetMapping("/all")
    public DataResult<List<User>> listAllUsers(){
        ArrayList<User> mergedUsers=new ArrayList<>();
        Iterator i= this.userService.listAllJobSeekers().getData().iterator();
        while(i.hasNext()){mergedUsers.add((User)i.next());}
        Iterator k= this.userService.listAllEmployers().getData().iterator();
        while(k.hasNext()){mergedUsers.add((User)k.next());}

        return new SuccessDataResult<>(mergedUsers);
    }
}
