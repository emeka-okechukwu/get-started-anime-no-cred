import React, { useState, useRef, useEffect } from "react";
import Hero from "./components/Hero/Hero";
import RecommendationList from "./components/RecommendationsList/RecommendationsList";
import DefaultList from "./components/DefaultList/DefaultList";
import Footer from "./components/Footer/Footer";
import "./App.css"; // If you have CSS styles

function App() {
  const [recommendations, setRecommendations] = useState<any[]>([]);
  const recommendationsRef = useRef<HTMLDivElement | null>(null);

  const handleRecommendationsUpdate = (updatedInput: any) => {
    setRecommendations(updatedInput);
  };

  useEffect(() => {
    if (recommendationsRef.current) {
      recommendationsRef.current.scrollIntoView({ behavior: "smooth", block: "start" });
    }
  }, [recommendations]);

  return (
    <>
      <main>
        <Hero onRecommendationsUpdate={handleRecommendationsUpdate} />
        {recommendations.length === 0 && <DefaultList />}
        {recommendations.length > 0 && (
          <div ref={recommendationsRef}>
            <RecommendationList recommendations={recommendations} />
          </div>
        )}
      </main>
      <footer>
        <Footer />
      </footer>
    </>
  );
}

export default App;
