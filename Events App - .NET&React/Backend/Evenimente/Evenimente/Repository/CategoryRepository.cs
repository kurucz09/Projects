using System;
using System.Collections.Generic;
using System.Linq;


namespace Evenimente.Repository
{
    public class CategoryRepository
    {
        public void Add(Category category)
        {
            using (var context = new EvenimenteEntities())
            {
                context.Categories.Add(category);
                context.SaveChanges();
            }
        }

        public List<Category> GetAll()
        {
            using (var context = new EvenimenteEntities())
            {
                var categories = context.Categories;
                if (categories.ToList().Count==0)
                    throw new Exception("No category could be found");
                return categories.ToList();
                
            }
        }

        public Category FindById(int categoryId)
        {
            using (var context = new EvenimenteEntities())
            {
                var findCategory = context.Categories.FirstOrDefault(category => category.Id == categoryId);
                if (findCategory == null)
                    throw new Exception("Category with this id could not be found");
                return findCategory;
            }
        }

        public void Update(Category category)
        {
            using (var context = new EvenimenteEntities())
            {
                var updateCategory = context.Categories.FirstOrDefault(categoryObj => categoryObj.Id == category.Id);

                if (updateCategory == null) throw new Exception("Category with this id does not exist"); ;
                updateCategory.Name = category.Name;
                updateCategory.PictureURL = category.PictureURL;
                context.SaveChanges();
            }
        }

        public void Delete(int categoryId)
        {
            using (var context = new EvenimenteEntities())
            {
                var deleteCategory = context.Categories.FirstOrDefault(category => category.Id == categoryId);
                if (deleteCategory == null) return;
                context.Categories.Remove(deleteCategory);
                context.SaveChanges();
            }
        }
    }
}
