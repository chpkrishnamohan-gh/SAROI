from pydantic import BaseModel
from typing import List

class ImageAnalysisResponse(BaseModel):
    caption: str
    embedding: List[float]

class TextEmbeddingResponse(BaseModel):
    embedding: List[float]
