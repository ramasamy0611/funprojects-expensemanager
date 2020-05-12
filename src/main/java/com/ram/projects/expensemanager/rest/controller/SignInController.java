package com.ram.projects.expensemanager.rest.controller;

import com.ram.projects.expensemanager.db.entity.ExpMgrSignIn;
import com.ram.projects.expensemanager.db.entity.ExpMgrSignUp;
import com.ram.projects.expensemanager.domain.user.ISingInManager;
import com.ram.projects.expensemanager.rest.RestResponse;
import com.ram.projects.expensemanager.rest.converter.Converter;
import com.ram.projects.expensemanager.rest.dto.SignIn;
import com.ram.projects.expensemanager.rest.dto.SignUp;
import com.ram.projects.expensemanager.rest.process.rest.RestProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

import static com.ram.projects.expensemanager.common.Constants.ROOT_END_POINT;

@RestController
@RequestMapping(ROOT_END_POINT + "/userAdmin")
public class SignInController {
  private RestProcessor restProcessor;
  private ISingInManager iSingInManager;
  private final Converter<SignIn, ExpMgrSignIn> singInInputConverter;
  private final Converter<ExpMgrSignIn, SignIn> singInOutputConverter;
  private final Converter<SignUp, ExpMgrSignUp> singUpInputConverter;

  public SignInController(
      RestProcessor restProcessor,
      ISingInManager iSingInManager,
      Converter<SignIn, ExpMgrSignIn> singInInputConverter,
      Converter<ExpMgrSignIn, SignIn> singInOutputConverter,
      Converter<SignUp, ExpMgrSignUp> singUpInputConverter) {
    this.restProcessor = restProcessor;
    this.iSingInManager = iSingInManager;
    this.singInInputConverter = singInInputConverter;
    this.singInOutputConverter = singInOutputConverter;
    this.singUpInputConverter = singUpInputConverter;
  }

  @PostMapping(path = "/signIn", consumes = "application/json", produces = "application/json")
  public CompletableFuture<ResponseEntity<SignIn>> singIn(
      @RequestBody SignIn signIn) {
    return restProcessor.process(
        "singIn",
        signIn,
        singInInputConverter,
        singInOutputConverter,
        signInInfo -> iSingInManager.signIn((ExpMgrSignIn) signInInfo));
  }

  @PostMapping(path = "/signUp", consumes = "application/json", produces = "application/json")
  public CompletableFuture<ResponseEntity<String>> singUp(
      @RequestBody SignUp signUp) {
    return restProcessor.process(
        "singUp",
        signUp,
        singUpInputConverter,
        str -> str,
        expMgrSignUp -> iSingInManager.signUp((ExpMgrSignUp) expMgrSignUp));
  }
}
