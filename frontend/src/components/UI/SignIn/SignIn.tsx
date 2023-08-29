import ButtonCard from "../ButtonCard/ButtonCard";
import classes from "./SignIn.module.css";

const SignInBtn = (props: any) => {
  return (
    <ButtonCard className={classes.btnSignIn} type={props.type}>
      <a href="https://github.com/emeka-okechukwu-dev/get-started-anime.git" target="_blank" rel="noopener noreferrer"> GitHub</a>
    </ButtonCard>
  );
};

export default SignInBtn;
