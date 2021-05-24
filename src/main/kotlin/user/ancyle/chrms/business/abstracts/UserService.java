package user.ancyle.chrms.business.abstracts;

import user.ancyle.chrms.core.utilities.result.DataResult;
import user.ancyle.chrms.core.utilities.result.Result;
import user.ancyle.chrms.entities.concretes.Employer;
import user.ancyle.chrms.entities.concretes.JobSeeker;
import user.ancyle.chrms.entities.concretes.User;

import java.util.List;

public interface UserService {
    // For single user
    public Result newUser(User user,String password);
    public Result updateUser(User user,String password);
    public Result deleteUser(User user);
    public Result deleteUserById(short id);
    public DataResult<List<User>> listAllUsers();
    public DataResult<User> getUserByMail(String mail);
    public DataResult<User> getUser(short id);
    // For Employer
    public Result newEmployer(Employer employer,String password);
    public Result updateEmployer(Employer employer,String password);
    public DataResult<List<Employer>> listAllEmployers();
    public DataResult<Employer> getEmployerByMail(String mail);
    public DataResult<Employer> getEmployer(short id);
    //For JobSeeker
    public Result newJobSeeker(JobSeeker jobSeeker,String password);
    public Result updateJobSeeker(JobSeeker jobSeeker,String password);
    public DataResult<List<JobSeeker>> listAllJobSeekers();
    public DataResult<JobSeeker> getJobSeekerByMail(String mail);
    public DataResult<JobSeeker> getJobSeeker(short id);
}
