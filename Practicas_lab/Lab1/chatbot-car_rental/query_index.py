from langchain_ollama import ChatOllama
import pickle
import os
import time

def load_vector_index():
    """Load the saved vector index"""
    if not os.path.exists("saved_index/vector_index_llama3_2_rentals.pkl"):
        print("Error: Vector index not found. Please run create_index.py first.")
        return None
    
    with open("saved_index/vector_index_llama3_2_rentals.pkl", "rb") as f:
        index = pickle.load(f)
    
    print("Vector index loaded successfully!")
    return index

def query_chatbot():
    """Interactive chatbot for querying the vector index"""
    
    # Load the vector index
    index = load_vector_index()
    if index is None:
        return
    
    # Create a ChatOllama object
    chat_llama3 = ChatOllama(model="llama3.2", temperature=0.7)
    
    print("Chatbot ready! Type 'exit' to quit.")
    print("-" * 40)
    
    prompt = ""
    while prompt.lower() != "exit":
        prompt = input("Enter your query: ")
        
        if prompt.lower() == "exit":
            print("Goodbye!")
            break
            
        try:
            start_time = time.time()
            answer = index.query(prompt, llm=chat_llama3)
            end_time = time.time()
            
            duration = end_time - start_time
            print(f"Llama3 Chatbot: {answer}")
            print(f"Query answered in {duration:.2f} seconds.")
            print("-" * 40)
        except Exception as e:
            print(f"Error: {e}")
            print("-" * 40)

if __name__ == "__main__":
    query_chatbot()
