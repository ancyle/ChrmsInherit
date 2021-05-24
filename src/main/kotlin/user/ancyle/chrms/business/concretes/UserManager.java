package user.ancyle.chrms.business.concretes;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.ancyle.chrms.business.abstracts.UserService;
import user.ancyle.chrms.business.constants.Messages;
import user.ancyle.chrms.core.utilities.business.RuleVerifier;
import user.ancyle.chrms.core.utilities.identifier.mernis.IKLKPSPublicSoap;
import user.ancyle.chrms.core.utilities.result.*;
import user.ancyle.chrms.dataAccess.abstracts.JobSeekerRepo;
import user.ancyle.chrms.dataAccess.abstracts.UserRepo;
import user.ancyle.chrms.dataAccess.abstracts.EmployerRepo;
import user.ancyle.chrms.entities.concretes.Employer;
import user.ancyle.chrms.entities.concretes.JobSeeker;
import user.ancyle.chrms.entities.concretes.User;

import java.util.List;
@Service
public class UserManager implements UserService {

    private final UserRepo userRepo;
    private final JobSeekerRepo jobSeekerRepo;
    private final EmployerRepo employerRepo;

    //Constructor
    @Autowired
    public UserManager(UserRepo userRepo,JobSeekerRepo jobSeekerRepo,EmployerRepo employerRepo) {
        this.userRepo = userRepo;
        this.jobSeekerRepo=jobSeekerRepo;
        this.employerRepo=employerRepo;
    }

    //Business

    //Employer
    @Override
    public Result newEmployer(Employer employer,String password) {
        var result= RuleVerifier.verify
                (checkCorpMailValid(employer),checkPasswordsAreMatching(employer,password),
                        checkUserExistsByMail(employer));
        if(result!=null) return new ErrorResult(result.getMessage());
        this.userRepo.save(employer);
        return new SuccessResult(Messages.success);
    }

    @Override
    public Result updateEmployer(Employer employer,String password) {
        var result= RuleVerifier.verify
                (checkCorpMailValid(employer),checkPasswordsAreMatching(employer,password),
                        checkUserExistsByMail(employer));
        if(result!=null) return new ErrorResult(result.getMessage());
        this.userRepo.save(employer);
        return new SuccessResult(Messages.success);
    }

    @Override
    public DataResult<List<Employer>> listAllEmployers() {
        return new SuccessDataResult<>(this.employerRepo.findAll(),Messages.success);
    }

    @Override
    public DataResult<Employer> getEmployerByMail(String mail) {
        return new SuccessDataResult<>(this.employerRepo.findByUserMail(mail).get(),Messages.success);
    }

    @Override
    public DataResult<Employer> getEmployer(short id) {
        return new SuccessDataResult<>(this.employerRepo.findById(id).get(),Messages.success);
    }

    //JobSeeker
    @Override
    public Result newJobSeeker(JobSeeker jobSeeker,String password) {
        var result= RuleVerifier.verify
                (checkNationalIdentity(jobSeeker),checkPasswordsAreMatching(jobSeeker,password),
                        checkUserExistsByMail(jobSeeker),checkUserExistsByNationalId(jobSeeker));
        if(result!=null) return new ErrorResult(result.getMessage());
        this.userRepo.save(jobSeeker);
        return new SuccessResult(Messages.success);
    }

    @Override
    public Result updateJobSeeker(JobSeeker jobSeeker,String password) {
        var result= RuleVerifier.verify
                (checkNationalIdentity(jobSeeker),checkPasswordsAreMatching(jobSeeker,password),
                        checkUserExistsByMail(jobSeeker),checkUserExistsByNationalId(jobSeeker));
        if(result!=null) return new ErrorResult(result.getMessage());
        this.userRepo.save(jobSeeker);
        return new SuccessResult(Messages.success);
    }

    @Override
    public DataResult<List<JobSeeker>> listAllJobSeekers() {
        return new SuccessDataResult<>(this.jobSeekerRepo.findAll(),Messages.success);
    }

    @Override
    public DataResult<JobSeeker> getJobSeekerByMail(String mail) {
        return new SuccessDataResult<>(this.jobSeekerRepo.findByUserMail(mail).get(),Messages.success);
    }

    @Override
    public DataResult<JobSeeker> getJobSeeker(short id) {
        return new SuccessDataResult<>(this.jobSeekerRepo.findById(id).get(),Messages.success);
    }

    //User
    @Override
    public Result newUser(User user,String password) {
        return null;
    }

    @Override
    public DataResult<List<User>> listAllUsers() {
        return new SuccessDataResult<>(this.userRepo.findAll(), Messages.success);
    }

    @Override
    public DataResult<User> getUser(short id) {
        return new SuccessDataResult<>(this.userRepo.findById(id).get(), Messages.success);
    }

    @Override
    public Result updateUser(User user,String password) {
        return null;
    }

    @Override
    public Result deleteUser(User user) {
        this.userRepo.delete(user);
        return new SuccessResult(Messages.success);
    }

    @Override
    public DataResult<User> getUserByMail(String mail) {
        return new SuccessDataResult<>(this.userRepo.findByUserMail(mail).get(),Messages.success);
    }

    @Override
    public Result deleteUserById(short id) {
        this.userRepo.deleteById(id);
        return new SuccessResult(Messages.success);
    }

    //Business Rules
  @SneakyThrows
  private Result checkNationalIdentity(JobSeeker jobSeeker){
      IKLKPSPublicSoap soap=new IKLKPSPublicSoap();
      var result=soap.TCKimlikNoDogrula(jobSeeker.getNationalId(),jobSeeker.getFirstName(),
              jobSeeker.getLastName(),Integer.valueOf(jobSeeker.getBirthYear()));
      if(!result) return new ErrorResult(Messages.invalidPerson);
      return new SuccessResult();
  }

  private Result checkCorpMailValid(Employer employer){
        var result=employer.getUserMail().contains(employer.getCorpName());
        if(!result) return new ErrorResult(Messages.corpMailInvalid);
        return new SuccessResult();
  }

  private Result checkPasswordsAreMatching(User user,String password){
        var result=user.getUserPass().equals(password);
        if(!result) return new ErrorResult(Messages.passwordDif);
        return new SuccessResult();
  }

  private Result checkUserExistsByMail(User user){
        var result=this.userRepo.findByUserMail(user.getUserMail()).isPresent();
        if(result) return new ErrorResult(Messages.userExists);
        return new SuccessResult();
  }

    private Result checkUserExistsByNationalId(JobSeeker jobSeeker){
        var result=this.jobSeekerRepo.findByNationalId(jobSeeker.getNationalId()).isPresent();
        if(result) return new ErrorResult(Messages.userExists);
        return new SuccessResult();
    }
}
