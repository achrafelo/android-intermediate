json.array!(@posts) do |post|
  json.extract! post, :id, :userId, :title, :body
  json.url post_url(post, format: :json)
end
