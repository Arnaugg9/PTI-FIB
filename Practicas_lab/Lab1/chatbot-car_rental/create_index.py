from langchain.indexes import VectorstoreIndexCreator
from langchain_community.document_loaders import TextLoader
from langchain_community.document_loaders import PyPDFLoader
from langchain_community.embeddings import OllamaEmbeddings
import pickle
import os
import time

def create_vector_index():
    """Create and save vector index from the dataset"""
    
    start_time = time.time()
    print(f"[{time.strftime('%Y-%m-%d %H:%M:%S')}] Indexing started...")

    # Create a TextLoader object
    loader = TextLoader("dataset/rentals.csv")

    # Create a PyPDFLoader object
    # loader = PyPDFLoader("dataset/rentals.pdf")
  
    # Create an OllamaEmbeddings object
    embeddings = OllamaEmbeddings(model="llama3.2")
    
    # Create a VectorstoreIndexCreator object
    index_creator = VectorstoreIndexCreator(embedding=embeddings)

    # Call from_loaders method
    index = index_creator.from_loaders([loader])
    
    # Save the index to disk
    os.makedirs("saved_index", exist_ok=True)
    with open("saved_index/vector_index_llama3_2_rentals.pkl", "wb") as f:
        pickle.dump(index, f)
    
    end_time = time.time()
    duration = end_time - start_time
    print(f"[{time.strftime('%Y-%m-%d %H:%M:%S')}] Indexing completed in {duration:.2f} seconds!")

if __name__ == "__main__":
    create_vector_index()
