import classes from "./Hero.module.css";
import React, { BaseSyntheticEvent, useState } from "react";
import ButtonCard from "../UI/ButtonCard/ButtonCard";
import InputCard from "../UI/InputCard/InputCard";
import axios from "axios";
import { API_BASE_URL } from "../../config";
import Navbar from "../NavBar/Navbar";
import AppBar from '@mui/material/AppBar'
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import CircularProgress from '@mui/material/CircularProgress';

import { styled } from '@mui/material/styles'
import { grey } from '@mui/material/colors'

const BorderedBottomBox = styled(Box)(({ theme }) => ({
  backgroundColor: theme.palette.common.black,
  borderBottom: `8px solid ${grey[900]}`
}))

interface MovieInputFormProps {
  onRecommendationsUpdate: (data: any) => void;
}

function Hero({ onRecommendationsUpdate }: MovieInputFormProps){

  const [movieTitles, setMovieTitles] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setMovieTitles(event.target.value);
  };

  const sendInput = async () => {
    setLoading(true);
    try {
      const response = await axios.post(
        `${API_BASE_URL}/recommend`,
        { input: movieTitles } // Sending data as an object
      );
      onRecommendationsUpdate(response.data);
    } catch (error) {
      console.error("Error sending list:", error);
    }
    setLoading(false);
  };

  const activateInput = (e: BaseSyntheticEvent) => {
    const el = document.querySelector(
      `.${classes.emailInputContainer} #hero_create_account`
    ) as HTMLInputElement;
  
    const label = document.querySelector(
      `.${classes.emailInputContainer} #hero_label`
    ) as HTMLLabelElement;
  
    el.focus();
    label.classList.add(classes.activeLabel);
  };
  
  const inputBlur = (e: BaseSyntheticEvent) => {
    const label = document.querySelector(
      `.${classes.emailInputContainer} #hero_label`
    ) as HTMLLabelElement;
  
    if (e.target.value.trim().length === 0) {
      label.classList.remove(classes.activeLabel);
    }
  };

  return (
    <BorderedBottomBox>
      <AppBar
        sx={{
          backgroundColor: 'transparent',
          padding: '25px 20px',
          position: 'absolute'
        }}
        elevation={0}
      >
        <Navbar />
      </AppBar>
      <Box
        sx={{
          position: 'relative',
          height: '745px',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          '&::after': {
            position: 'absolute',
            content: '""',
            width: '100%',
            height: '100%',
            top: 0,
            left: 0,
            background: 'rgba(0, 0, 0, 0.4)',
            backgroundImage: `linear-gradient(
              to bottom,
              rgba(0, 0, 0, 0.6) 0,
              rgba(0, 0, 0, 0) 60%,
              rgba(0, 0, 0, 0.8) 100%
            )`
          }
        }}
      >
        <img
          src="https://assets.nflxext.com/ffe/siteui/vlv3/00103100-5b45-4d4f-af32-342649f1bda5/4f0437a7-333c-42f9-801e-dce7a032c30c/CA-en-20230821-popsignuptwoweeks-perspective_alpha_website_large.jpg"
          alt="Backdrop Netflix"
          style={{ position: 'absolute', width: '100%', height: '100%', objectFit: 'cover' }}
        />
        <Container maxWidth="md" sx={{ position: 'relative', zIndex: 1000 }}>
          <div className={classes.hero}>
            <h1>Discover Your Perfect Anime.</h1>
            <p>Binge-watch wherever. Switch anytime.</p>
            <div className={`${classes.createAccountContainer}`}>
              <h3>
                Share favorite movies and TV shows for personalized anime suggestions.
              </h3>
              <div className={`${classes.createAccount}`}>
                <InputCard
                  clickHandler={activateInput}
                  key={"hero_email_input"}
                  className={classes.emailInputContainer}
                >
                  <label id="hero_label" htmlFor="hero_create_account">
                    What are your top 5 movies?
                  </label>
                  <input
                    onBlur={inputBlur}
                    type="text"
                    id="hero_create_account"
                    autoComplete="email"
                    value={movieTitles}
                    onChange={handleInputChange}
                  />
                </InputCard>

                <ButtonCard className={classes.getStartedContainer} onClick={sendInput}>
                  {loading ? (
                    <CircularProgress size={24} color="inherit" />
                  ) : (
                    <>
                      <span>Get List</span>
                      <svg
                        width="24"
                        height="24"
                        viewBox="0 0 24 24"
                        fill="none"
                        xmlns="http://www.w3.org/2000/svg"
                        className="Hawkins-Icon Hawkins-Icon-Standard"
                        data-name="ChevronRight"
                      >
                        <path
                          fillRule="evenodd"
                          clipRule="evenodd"
                          d="M7.29297 19.2928L14.5859 12L7.29297 4.70706L8.70718 3.29285L16.7072 11.2928C16.8947 11.4804 17.0001 11.7347 17.0001 12C17.0001 12.2652 16.8947 12.5195 16.7072 12.7071L8.70718 20.7071L7.29297 19.2928Z"
                          fill="currentColor"
                        ></path>
                      </svg>
                    </>
                  )}
                </ButtonCard>
              </div>
            </div>
            <p style={{textAlign: 'center', marginTop: '16px', fontSize: '16px'}}>
              * Please allow up to 5 seconds for list to load *
            </p>
          </div>
        </Container>
      </Box>
    </BorderedBottomBox>
  );
};

export default Hero;