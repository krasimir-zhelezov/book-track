from faker import Faker
import random
import requests

fake = Faker()

api_url = "http://localhost:8080/api/books/"

def generate_books(num_books):
    for _ in range(num_books):
        book = {
            "title": fake.catch_phrase(),
            "author": fake.name(),
            "isbn": fake.isbn13().replace('-', ''),
            "genres": [fake.random_element(elements=("Fiction", "Non-Fiction", "Sci-Fi", "Mystery", "Romance"))],
            "published": random.randrange(0, 2025)
        }
        
        response = requests.post(api_url, headers={'accept': '*/*', 'Content-Type': 'application/json'}, json=book)
        
        print(response.json())
        
generate_books(10)