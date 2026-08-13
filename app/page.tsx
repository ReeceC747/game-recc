"use client";

import "./globals.css";
import { useState } from "react";


export default function Home() 
{
  const [recommendedGame, setRecommendedGame] = useState<String | null>(null);
  async function getRecommendation()
  {
    const response = await fetch("http://localhost:8080/random");
    const game = await response.json();
    setRecommendedGame(game.name);
    console.log(game);
  }
  return (
    <div>
      <h1>
        GameRecc
      </h1>
      <div className="rectangle"></div>
      <RecommendationButton
        onRecommend={getRecommendation}
      />
       
      <Filter />
      <p>
        {recommendedGame}
      </p>
    </div>
  );
}

type RecommendationButtonProps = 
{
  onRecommend: () => void;
}

function RecommendationButton({ onRecommend }: RecommendationButtonProps)
{
  return (
    <button onClick={onRecommend}>
      Get Recommendation
    </button>
  );
}

function Filter()
{
  return(
    <div>
      <h2>Filters</h2>
      {/* Add filter options here */}
    </div>
  );
}