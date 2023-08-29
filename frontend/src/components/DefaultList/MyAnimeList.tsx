export interface Movie {
    id: number;
    title: string;
    backdrop: string;
    url: string;
}

export const movieList: Movie[] = [
    {
        id: 1,
        title: "Attack on Titan",
        backdrop: "https://image.tmdb.org/t/p/w500/8C5gYahMFmzHKGNID8QrG5t25WU.jpg",
        url: "https://www.themoviedb.org/tv/1429",
    },
    {
        id: 2,
        title: "Demon Slayer: Kimetsu no Yaiba",
        backdrop: "https://image.tmdb.org/t/p/w500/xUfRZu2mi8jH6SzQEJGP6tjBuYj.jpg",
        url: "https://www.themoviedb.org/tv/85937",
    },
    {
        id: 3,
        title: "Chainsaw Man",
        backdrop: "https://image.tmdb.org/t/p/w500/npdB6eFzizki0WaZ1OvKcJrWe97.jpg",
        url: "https://www.themoviedb.org/tv/114410",
    },
    {
        id: 4,
        title: "Hell's Paradise",
        backdrop: "https://image.tmdb.org/t/p/w500/hYHXIqdi8bmbU7oZqgu9GW8hm8j.jpg",
        url: "https://www.themoviedb.org/tv/117465",
    },
    {
        id: 5,
        title: "Hunter x Hunter",
        backdrop: "https://image.tmdb.org/t/p/w500/ucpgmUR1h5Te1BYegKItoPjOeF7.jpg",
        url: "https://www.themoviedb.org/tv/46298",
    },
    {
        id: 6,
        title: "Tokyo Ghoul",
        backdrop: "https://image.tmdb.org/t/p/w500/y2roOFsew9nJKKVwwJCHSyzgpK9.jpg",
        url: "https://www.themoviedb.org/tv/61374",
    },
];

