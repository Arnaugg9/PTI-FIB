from langchain_ollama import ChatOllama
import time

def query_chatbot():
    """Interactive chatbot without using the vector index (non-RAG mode)"""
    
    # Create a ChatOllama object
    chat_llama3 = ChatOllama(model="llama3.2", temperature=0.7)
    
    print("Chatbot (non-RAG) ready! Type 'exit' to quit.")
    print("-" * 40)
    
    prompt = ""
    while prompt.lower() != "exit":
        prompt = input("Enter your query: ")
        
        if prompt.lower() == "exit":
            print("Goodbye!")
            break
            
        try:
            start_time = time.time()
            # Direct query to the LLM, no vector index
            answer = chat_llama3.invoke(prompt)
            end_time = time.time()
            
            duration = end_time - start_time
            print(f"Llama3 Non-RAG Chatbot: {answer.content}")
            print(f"Query answered in {duration:.2f} seconds.")
            print("-" * 40)
        except Exception as e:
            print(f"Error: {e}")
            print("-" * 40)

if __name__ == "__main__":
    query_chatbot()
