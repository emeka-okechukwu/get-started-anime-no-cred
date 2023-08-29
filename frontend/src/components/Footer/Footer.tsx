import LanguageSelect from "../UI/LanguageSelect/LanguageSelect";
import classes from "./Footer.module.css";

const Footer = () => {
  return (
    <div className={classes.footerMaster}>
      <div className={classes.footer}>
        <span>
          This project was built with ❤️ and notably
        </span>
        <div>
            <ul className={classes.contactList}>
                <li>
                    <a href="https://react.dev" target="_blank" rel="noopener noreferrer">React (+ TypeScript, Material-U)</a>
                </li>
                <li>
                    <a href="https://openai.com/blog/openai-api" target="_blank" rel="noopener noreferrer">OpenAI API</a>
                </li>
                <li>
                    <a href="https://developer.themoviedb.org/reference/intro/getting-started" target="_blank" rel="noopener noreferrer">The Movie Database API</a>
                </li>
            </ul>
        </div>
        <div className={classes.footerLangSelect}>
          <LanguageSelect id="footer_lang_select" className="" />
        </div>
        <span className={classes.copyright}>Get Started Anime</span>
        <div className={classes.attribution}>
            Coded by:{" "}
            <a href="https://github.com/emeka-okechukwu-dev" target="_blank" rel="noopener noreferrer">Emeka Okechukwu</a>
            <br />
            Original site:{" "}
            <a href="https://www.netflix.com/ca/" target="_blank" rel="noopener noreferrer">Netflix Canada</a>
        </div>
      </div>
    </div>
  );
};

export default Footer;
