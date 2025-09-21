export default interface Book {
    id: string,
    title: string,
    author: string,
    isbn: string,
    genres: string[],
    published: number,
    createdAt: string
}