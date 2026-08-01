import "./globals.css";

export default function Home() 
{
  return (
    <div>
      <h1>
        GameRecc
      </h1>
      <div className="rectangle"></div>
      <RecommendationButton />
      <Filter />
    </div>
  );

}

function RecommendationButton()
{
  return (
    <button>
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